package eu.epitech.dto;

import eu.epitech.model.Rank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class JediDto {
    @NotEmpty
    @Size(min = 1, max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;
    private Rank rank;
    private boolean isMemberOfCouncil;
    private LocalDate birthDate;

    public JediDto() {
    }

    public JediDto(String firstName, String lastName, Rank rank, boolean isMemberOfCouncil, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.isMemberOfCouncil = isMemberOfCouncil;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isMemberOfCouncil() {
        return isMemberOfCouncil;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
