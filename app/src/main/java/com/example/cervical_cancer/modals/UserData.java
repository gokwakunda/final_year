
package com.example.cervical_cancer.modals;
public class UserData {
    private String email;
    private int prediction;
    private int age;
    private int numberOfSexualPartners;
    private int firstSexualIntercourse;
    private int numOfPregnancies;
    private int smokes;
    private int smokesYears;
    private int smokesPacksYear;
    private int hormonalContraceptives;
    private int hormonalContraceptivesYears;
    private int iud;
    private int iudYears;
    private int stds;
    private int stdsNumber;
    private int stdsCondylomatosis;
    private int stdsCervicalCondylomatosis;
    private int stdsVaginalCondylomatosis;
    private int stdsVulvoPerinealCondylomatosis;
    private int stdsSyphilis;
    private int stdsPelvicInflammatoryDisease;
    private int stdsGenitalHerpes;
    private int stdsMolluscumContagiosum;
    private int stdsAIDS;
    private int stdsHIV;
    private int stdsHepatitisB;
    private int stdsHPV;
    private int stdsNumberOfDiagnosis;
    private int stdsTimeSinceFirstDiagnosis;
    private int stdsTimeSinceLastDiagnosis;

    public UserData() {
        // Required empty constructor for Firebase
    }

    // Add constructor and getters/setters here

    public UserData(String email, int prediction, int age, int numberOfSexualPartners, int firstSexualIntercourse,
                    int numOfPregnancies, int smokes, int smokesYears, int smokesPacksYear,
                    int hormonalContraceptives, int hormonalContraceptivesYears, int iud, int iudYears,
                    int stds, int stdsNumber, int stdsCondylomatosis, int stdsCervicalCondylomatosis,
                    int stdsVaginalCondylomatosis, int stdsVulvoPerinealCondylomatosis, int stdsSyphilis,
                    int stdsPelvicInflammatoryDisease, int stdsGenitalHerpes, int stdsMolluscumContagiosum,
                    int stdsAIDS, int stdsHIV, int stdsHepatitisB, int stdsHPV, int stdsNumberOfDiagnosis,
                    int stdsTimeSinceFirstDiagnosis, int stdsTimeSinceLastDiagnosis) {
        this.email = email;
        this.prediction = prediction;
        this.age = age;
        this.numberOfSexualPartners = numberOfSexualPartners;
        this.firstSexualIntercourse = firstSexualIntercourse;
        this.numOfPregnancies = numOfPregnancies;
        this.smokes = smokes;
        this.smokesYears = smokesYears;
        this.smokesPacksYear = smokesPacksYear;
        this.hormonalContraceptives = hormonalContraceptives;
        this.hormonalContraceptivesYears = hormonalContraceptivesYears;
        this.iud = iud;
        this.iudYears = iudYears;
        this.stds = stds;
        this.stdsNumber = stdsNumber;
        this.stdsCondylomatosis = stdsCondylomatosis;
        this.stdsCervicalCondylomatosis = stdsCervicalCondylomatosis;
        this.stdsVaginalCondylomatosis = stdsVaginalCondylomatosis;
        this.stdsVulvoPerinealCondylomatosis = stdsVulvoPerinealCondylomatosis;
        this.stdsSyphilis = stdsSyphilis;
        this.stdsPelvicInflammatoryDisease = stdsPelvicInflammatoryDisease;
        this.stdsGenitalHerpes = stdsGenitalHerpes;
        this.stdsMolluscumContagiosum = stdsMolluscumContagiosum;
        this.stdsAIDS = stdsAIDS;
        this.stdsHIV = stdsHIV;
        this.stdsHepatitisB = stdsHepatitisB;
        this.stdsHPV = stdsHPV;
        this.stdsNumberOfDiagnosis = stdsNumberOfDiagnosis;
        this.stdsTimeSinceFirstDiagnosis = stdsTimeSinceFirstDiagnosis;
        this.stdsTimeSinceLastDiagnosis = stdsTimeSinceLastDiagnosis;
    }



    public int getPrediction() {
        return prediction;
    }
    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberOfSexualPartners() {
        return numberOfSexualPartners;
    }

    public void setNumberOfSexualPartners(int numberOfSexualPartners) {
        this.numberOfSexualPartners = numberOfSexualPartners;
    }

    public int getFirstSexualIntercourse() {
        return firstSexualIntercourse;
    }

    public void setFirstSexualIntercourse(int firstSexualIntercourse) {
        this.firstSexualIntercourse = firstSexualIntercourse;
    }

    public int getNumOfPregnancies() {
        return numOfPregnancies;
    }

    public void setNumOfPregnancies(int numOfPregnancies) {
        this.numOfPregnancies = numOfPregnancies;
    }

    public int getSmokes() {
        return smokes;
    }

    public void setSmokes(int smokes) {
        this.smokes = smokes;
    }

    public int getSmokesYears() {
        return smokesYears;
    }

    public void setSmokesYears(int smokesYears) {
        this.smokesYears = smokesYears;
    }

    public int getSmokesPacksYear() {
        return smokesPacksYear;
    }

    public void setSmokesPacksYear(int smokesPacksYear) {
        this.smokesPacksYear = smokesPacksYear;
    }

    public int getHormonalContraceptives() {
        return hormonalContraceptives;
    }

    public void setHormonalContraceptives(int hormonalContraceptives) {
        this.hormonalContraceptives = hormonalContraceptives;
    }

    public int getHormonalContraceptivesYears() {
        return hormonalContraceptivesYears;
    }

    public void setHormonalContraceptivesYears(int hormonalContraceptivesYears) {
        this.hormonalContraceptivesYears = hormonalContraceptivesYears;
    }

    public int getIud() {
        return iud;
    }

    public void setIud(int iud) {
        this.iud = iud;
    }

    public int getIudYears() {
        return iudYears;
    }

    public void setIudYears(int iudYears) {
        this.iudYears = iudYears;
    }

    public int getStds() {
        return stds;
    }

    public void setStds(int stds) {
        this.stds = stds;
    }

    public int getStdsNumber() {
        return stdsNumber;
    }

    public void setStdsNumber(int stdsNumber) {
        this.stdsNumber = stdsNumber;
    }

    public int getStdsCondylomatosis() {
        return stdsCondylomatosis;
    }

    public void setStdsCondylomatosis(int stdsCondylomatosis) {
        this.stdsCondylomatosis = stdsCondylomatosis;
    }

    public int getStdsCervicalCondylomatosis() {
        return stdsCervicalCondylomatosis;
    }

    public void setStdsCervicalCondylomatosis(int stdsCervicalCondylomatosis) {
        this.stdsCervicalCondylomatosis = stdsCervicalCondylomatosis;
    }

    public int getStdsVaginalCondylomatosis() {
        return stdsVaginalCondylomatosis;
    }

    public void setStdsVaginalCondylomatosis(int stdsVaginalCondylomatosis) {
        this.stdsVaginalCondylomatosis = stdsVaginalCondylomatosis;
    }

    public int getStdsVulvoPerinealCondylomatosis() {
        return stdsVulvoPerinealCondylomatosis;
    }

    public void setStdsVulvoPerinealCondylomatosis(int stdsVulvoPerinealCondylomatosis) {
        this.stdsVulvoPerinealCondylomatosis = stdsVulvoPerinealCondylomatosis;
    }

    public int getStdsSyphilis() {
        return stdsSyphilis;
    }

    public void setStdsSyphilis(int stdsSyphilis) {
        this.stdsSyphilis = stdsSyphilis;
    }

    public int getStdsPelvicInflammatoryDisease() {
        return stdsPelvicInflammatoryDisease;
    }

    public void setStdsPelvicInflammatoryDisease(int stdsPelvicInflammatoryDisease) {
        this.stdsPelvicInflammatoryDisease = stdsPelvicInflammatoryDisease;
    }

    public int getStdsGenitalHerpes() {
        return stdsGenitalHerpes;
    }

    public void setStdsGenitalHerpes(int stdsGenitalHerpes) {
        this.stdsGenitalHerpes = stdsGenitalHerpes;
    }

    public int getStdsMolluscumContagiosum() {
        return stdsMolluscumContagiosum;
    }

    public void setStdsMolluscumContagiosum(int stdsMolluscumContagiosum) {
        this.stdsMolluscumContagiosum = stdsMolluscumContagiosum;
    }

    public int getStdsAIDS() {
        return stdsAIDS;
    }

    public void setStdsAIDS(int stdsAIDS) {
        this.stdsAIDS = stdsAIDS;
    }

    public int getStdsHIV() {
        return stdsHIV;
    }

    public void setStdsHIV(int stdsHIV) {
        this.stdsHIV = stdsHIV;
    }

    public int getStdsHepatitisB() {
        return stdsHepatitisB;
    }

    public void setStdsHepatitisB(int stdsHepatitisB) {
        this.stdsHepatitisB = stdsHepatitisB;
    }

    public int getStdsHPV() {
        return stdsHPV;
    }

    public void setStdsHPV(int stdsHPV) {
        this.stdsHPV = stdsHPV;
    }

    public int getStdsNumberOfDiagnosis() {
        return stdsNumberOfDiagnosis;
    }

    public void setStdsNumberOfDiagnosis(int stdsNumberOfDiagnosis) {
        this.stdsNumberOfDiagnosis = stdsNumberOfDiagnosis;
    }

    public int getStdsTimeSinceFirstDiagnosis() {
        return stdsTimeSinceFirstDiagnosis;
    }

    public void setStdsTimeSinceFirstDiagnosis(int stdsTimeSinceFirstDiagnosis) {
        this.stdsTimeSinceFirstDiagnosis = stdsTimeSinceFirstDiagnosis;
    }

    public int getStdsTimeSinceLastDiagnosis() {
        return stdsTimeSinceLastDiagnosis;
    }

    public void setStdsTimeSinceLastDiagnosis(int stdsTimeSinceLastDiagnosis) {
        this.stdsTimeSinceLastDiagnosis = stdsTimeSinceLastDiagnosis;
    }


}

//public class UserData {
//    private String email;
//    private int age;
//    private int numPartners;
//    private int firstIntercourse;
//    private int numPregnancies;
//    private int smokes;
//    private int smokesYears;
//    private int smokesPacksYear;
//    private int hormonalContraceptives;
//    private int hormonalContraceptivesYears;
//    private int iud;
//    private int iudYears;
//    private int stds;
//    private int stdsNumber;
//    private int stdsCondylomatosis;
//    private int stdsCervicalCondylomatosis;
//    private int stdsVaginalCondylomatosis;
//    private int stdsVulvoPerinealCondylomatosis;
//    private int stdsSyphilis;
//    private int stdsPelvicInflammatoryDisease;
//    private int stdsGenitalHerpes;
//    private int stdsMolluscumContagiosum;
//    private int stdsAIDS;
//    private int stdsHIV;
//    private int stdsHepatitisB;
//    private int stdsHPV;
//    private int stdsNumberOfDiagnosis;
//    private int stdsTimeSinceFirstDiagnosis;
//    private int stdsTimeSinceLastDiagnosis;
//
//    public UserData() {
//
//    }
//
//    // Constructors, getters, setters, etc.
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public int getNumPartners() {
//        return numPartners;
//    }
//
//    public void setNumPartners(int numPartners) {
//        this.numPartners = numPartners;
//    }
//
//    public int getFirstIntercourse() {
//        return firstIntercourse;
//    }
//
//    public void setFirstIntercourse(int firstIntercourse) {
//        this.firstIntercourse = firstIntercourse;
//    }
//
//    public int getNumPregnancies() {
//        return numPregnancies;
//    }
//
//    public void setNumPregnancies(int numPregnancies) {
//        this.numPregnancies = numPregnancies;
//    }
//
//    public int getSmokes() {
//        return smokes;
//    }
//
//    public void setSmokes(int smokes) {
//        this.smokes = smokes;
//    }
//
//    public int getSmokesYears() {
//        return smokesYears;
//    }
//
//    public void setSmokesYears(int smokesYears) {
//        this.smokesYears = smokesYears;
//    }
//
//    public int getSmokesPacksYear() {
//        return smokesPacksYear;
//    }
//
//    public void setSmokesPacksYear(int smokesPacksYear) {
//        this.smokesPacksYear = smokesPacksYear;
//    }
//
//    public int getHormonalContraceptives() {
//        return hormonalContraceptives;
//    }
//
//    public void setHormonalContraceptives(int hormonalContraceptives) {
//        this.hormonalContraceptives = hormonalContraceptives;
//    }
//
//    public int getHormonalContraceptivesYears() {
//        return hormonalContraceptivesYears;
//    }
//
//    public void setHormonalContraceptivesYears(int hormonalContraceptivesYears) {
//        this.hormonalContraceptivesYears = hormonalContraceptivesYears;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public int getIud() {
//        return iud;
//    }
//
//    public UserData(String email,int age, int numPartners, int firstIntercourse, int numPregnancies, int smokes, int smokesYears, int smokesPacksYear, int hormonalContraceptives, int hormonalContraceptivesYears, int iud, int iudYears, int stds, int stdsNumber, int stdsCondylomatosis, int stdsCervicalCondylomatosis, int stdsVaginalCondylomatosis, int stdsVulvoPerinealCondylomatosis, int stdsSyphilis, int stdsPelvicInflammatoryDisease, int stdsGenitalHerpes, int stdsMolluscumContagiosum, int stdsAIDS, int stdsHIV, int stdsHepatitisB, int stdsHPV, int stdsNumberOfDiagnosis, int stdsTimeSinceFirstDiagnosis, int stdsTimeSinceLastDiagnosis) {
//        this.email = email;
//        this.age = age;
//        this.numPartners = numPartners;
//        this.firstIntercourse = firstIntercourse;
//        this.numPregnancies = numPregnancies;
//        this.smokes = smokes;
//        this.smokesYears = smokesYears;
//        this.smokesPacksYear = smokesPacksYear;
//        this.hormonalContraceptives = hormonalContraceptives;
//        this.hormonalContraceptivesYears = hormonalContraceptivesYears;
//        this.iud = iud;
//        this.iudYears = iudYears;
//        this.stds = stds;
//        this.stdsNumber = stdsNumber;
//        this.stdsCondylomatosis = stdsCondylomatosis;
//        this.stdsCervicalCondylomatosis = stdsCervicalCondylomatosis;
//        this.stdsVaginalCondylomatosis = stdsVaginalCondylomatosis;
//        this.stdsVulvoPerinealCondylomatosis = stdsVulvoPerinealCondylomatosis;
//        this.stdsSyphilis = stdsSyphilis;
//        this.stdsPelvicInflammatoryDisease = stdsPelvicInflammatoryDisease;
//        this.stdsGenitalHerpes = stdsGenitalHerpes;
//        this.stdsMolluscumContagiosum = stdsMolluscumContagiosum;
//        this.stdsAIDS = stdsAIDS;
//        this.stdsHIV = stdsHIV;
//        this.stdsHepatitisB = stdsHepatitisB;
//        this.stdsHPV = stdsHPV;
//        this.stdsNumberOfDiagnosis = stdsNumberOfDiagnosis;
//        this.stdsTimeSinceFirstDiagnosis = stdsTimeSinceFirstDiagnosis;
//        this.stdsTimeSinceLastDiagnosis = stdsTimeSinceLastDiagnosis;
//    }
//
//    public void setIud(int iud) {
//        this.iud = iud;
//    }
//
//    public int getIudYears() {
//        return iudYears;
//    }
//
//    public void setIudYears(int iudYears) {
//        this.iudYears = iudYears;
//    }
//
//    public int getStds() {
//        return stds;
//    }
//
//    public void setStds(int stds) {
//        this.stds = stds;
//    }
//
//    public int getStdsNumber() {
//        return stdsNumber;
//    }
//
//    public void setStdsNumber(int stdsNumber) {
//        this.stdsNumber = stdsNumber;
//    }
//
//    public int getStdsCondylomatosis() {
//        return stdsCondylomatosis;
//    }
//
//    public void setStdsCondylomatosis(int stdsCondylomatosis) {
//        this.stdsCondylomatosis = stdsCondylomatosis;
//    }
//
//    public int getStdsCervicalCondylomatosis() {
//        return stdsCervicalCondylomatosis;
//    }
//
//    public void setStdsCervicalCondylomatosis(int stdsCervicalCondylomatosis) {
//        this.stdsCervicalCondylomatosis = stdsCervicalCondylomatosis;
//    }
//
//    public int getStdsVaginalCondylomatosis() {
//        return stdsVaginalCondylomatosis;
//    }
//
//    public void setStdsVaginalCondylomatosis(int stdsVaginalCondylomatosis) {
//        this.stdsVaginalCondylomatosis = stdsVaginalCondylomatosis;
//    }
//
//    public int getStdsVulvoPerinealCondylomatosis() {
//        return stdsVulvoPerinealCondylomatosis;
//    }
//
//    public void setStdsVulvoPerinealCondylomatosis(int stdsVulvoPerinealCondylomatosis) {
//        this.stdsVulvoPerinealCondylomatosis = stdsVulvoPerinealCondylomatosis;
//    }
//
//    public int getStdsSyphilis() {
//        return stdsSyphilis;
//    }
//
//    public void setStdsSyphilis(int stdsSyphilis) {
//        this.stdsSyphilis = stdsSyphilis;
//    }
//
//    public int getStdsPelvicInflammatoryDisease() {
//        return stdsPelvicInflammatoryDisease;
//    }
//
//    public void setStdsPelvicInflammatoryDisease(int stdsPelvicInflammatoryDisease) {
//        this.stdsPelvicInflammatoryDisease = stdsPelvicInflammatoryDisease;
//    }
//
//    public int getStdsGenitalHerpes() {
//        return stdsGenitalHerpes;
//    }
//
//    public void setStdsGenitalHerpes(int stdsGenitalHerpes) {
//        this.stdsGenitalHerpes = stdsGenitalHerpes;
//    }
//
//    public int getStdsMolluscumContagiosum() {
//        return stdsMolluscumContagiosum;
//    }
//
//    public void setStdsMolluscumContagiosum(int stdsMolluscumContagiosum) {
//        this.stdsMolluscumContagiosum = stdsMolluscumContagiosum;
//    }
//
//    public int getStdsAIDS() {
//        return stdsAIDS;
//    }
//
//    public void setStdsAIDS(int stdsAIDS) {
//        this.stdsAIDS = stdsAIDS;
//    }
//
//    public int getStdsHIV() {
//        return stdsHIV;
//    }
//
//    public void setStdsHIV(int stdsHIV) {
//        this.stdsHIV = stdsHIV;
//    }
//
//    public int getStdsHepatitisB() {
//        return stdsHepatitisB;
//    }
//
//    public void setStdsHepatitisB(int stdsHepatitisB) {
//        this.stdsHepatitisB = stdsHepatitisB;
//    }
//
//    public int getStdsHPV() {
//        return stdsHPV;
//    }
//
//    public void setStdsHPV(int stdsHPV) {
//        this.stdsHPV = stdsHPV;
//    }
//
//    public int getStdsNumberOfDiagnosis() {
//        return stdsNumberOfDiagnosis;
//    }
//
//    public void setStdsNumberOfDiagnosis(int stdsNumberOfDiagnosis) {
//        this.stdsNumberOfDiagnosis = stdsNumberOfDiagnosis;
//    }
//
//    public int getStdsTimeSinceFirstDiagnosis() {
//        return stdsTimeSinceFirstDiagnosis;
//    }
//
//    public void setStdsTimeSinceFirstDiagnosis(int stdsTimeSinceFirstDiagnosis) {
//        this.stdsTimeSinceFirstDiagnosis = stdsTimeSinceFirstDiagnosis;
//    }
//
//    public int getStdsTimeSinceLastDiagnosis() {
//        return stdsTimeSinceLastDiagnosis;
//    }
//
//    public void setStdsTimeSinceLastDiagnosis(int stdsTimeSinceLastDiagnosis) {
//        this.stdsTimeSinceLastDiagnosis = stdsTimeSinceLastDiagnosis;
//    }
//
//
//}
