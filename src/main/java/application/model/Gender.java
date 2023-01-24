package application.model;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    GENDERLESS("Genderless"),
    UNKNOWN("Unknown");

    private String value;

    Gender(String value) {
        this.value = value;
    }
}
