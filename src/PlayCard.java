/**
 * Created by Hadassah on 25/09/2016.
 * This class is a child class of the abstract class Card which holds properties of the play cards in class variables.
 */
public class PlayCard extends Card {
    String chemicalStructure;
    String classification;
    String cleavage;
    String crustalAbundance;
    String crystalSystem;
    String economicValue;
    String hardness;
    String occurance;
    String specificGravity;

    public PlayCard(int cardIndex, String chemicalStructure, String classification, String cleavage,
                    String crustalAbundance, String crystalSystem, String economicValue, String fileName,
                    String hardness, String imageName, String occurance, String specificGravity, String name) {
        super(cardIndex, fileName, imageName, name);
        this.chemicalStructure = chemicalStructure;
        this.classification = classification;
        this.cleavage = cleavage.trim();
        this.crustalAbundance = crustalAbundance.trim();
        this.crystalSystem = crystalSystem;
        this.economicValue = economicValue.trim();
        this.hardness = hardness.trim();
        this.occurance = occurance;
        this.specificGravity = specificGravity.trim();
    }

    public boolean validPlay(Card lastCard, int currentCategoryIndex) {
        if (lastCard instanceof PlayCard) {
            PlayCard playCard = (PlayCard) lastCard;
            /* returns true if the the card is higher than the 'lastCard' in the current category */
            switch (currentCategoryIndex) {
                case 0:
                    return splitValues(this.hardness) > splitValues(playCard.hardness);
                case 1:
                    return splitValues(this.specificGravity) > splitValues(playCard.specificGravity);
                case 2:
                    return splitCleavage(this.cleavage) > splitCleavage(playCard.cleavage);
                case 3:
                    return splitAbundance(this.crustalAbundance) > splitAbundance(playCard.crustalAbundance);
                case 4:
                    return splitEconomicValue(this.economicValue) > splitEconomicValue(playCard.economicValue);
                default:
                    return false;
            }
        } else {
            return true;                    // validPlay always returns true for any trump card
        }
    }

    public String toString()  {
        return name + " (" + chemicalStructure + "): Hardness: " + hardness + ", Specific Gravity: " + specificGravity
                + ", Cleavage: " + cleavage + ", Crustal Abundance: " + crustalAbundance + ", Economic Value: "
                + economicValue;
    }

    public double splitValues(String string) {
        string = string.replaceAll("\\s+","");                      // remove all whitespace from the string
        /* If the string contains a hyphen, take the value after the hyphen, else take the value. Convert to double. */
        double higherValue;
        int index = string.indexOf('-');
        if (index == -1) {
            higherValue = Double.parseDouble(string);
        } else {
            String stringHigherValue = string.substring(index + 1);
            higherValue = Double.parseDouble(stringHigherValue);
        }
        return higherValue;
    }

    public int splitCleavage(String string)  {
        /* cleavage comparitors have been hardcoded into an array */
        String[] cleavageValues = {"none", "poor/none", "1 poor", "2 poor", "1 good", "1 good, 1 poor", "2 good",
                "3 good", "1 perfect", "1 perfect, 1 good", "1 perfect, 2 good", "2 perfect, 1 good", "3 perfect",
                "4 perfect", "6 perfect"};
        /* returns the index of the array at which the string was found, or -1 if it was not found */
        for (int i = 0; i < cleavageValues.length; i++)  {
            if (string.equals(cleavageValues[i]))  {  return i;  }
        }
        System.out.println("Cleavage comparitor was not found");
        return -1;
    }

    public int splitAbundance(String string)  {
        /* crustal abundance comparitors have been hardcoded into an array */
        String[] crustalAbundanceValues = {"ultratrace", "trace", "low", "moderate", "high", "very high"};
        /* returns the index of the array at which the string was found, or -1 if it was not found */
        for (int i = 0; i < crustalAbundanceValues.length; i++)  {
            if (string.equals(crustalAbundanceValues[i]))  {  return i;  }
        }
        System.out.println("Crustal Abundance comparitor was not found");
        return -1;
    }

    public int splitEconomicValue(String string)  {
        /* Economic value comparitors have been hardcoded into an array */
        String[] economicValueValues = {"trivial", "low", "moderate", "high", "very high", "I'm rich!"};
        /* returns the index of the array at which the string was found, or -1 if it was not found */
        for (int i = 0; i < economicValueValues.length; i++)  {
            if (string.equals(economicValueValues[i]))  {  return i;  }
        }
        System.out.println("Economic Value comparitor was not found");
        return -1;
    }
}
