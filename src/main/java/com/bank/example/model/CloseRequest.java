package com.bank.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CloseRequest {

    @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "closeRequest_generator")
    @SequenceGenerator(sequenceName = "closeRequest_sequence", name = "closeRequest_generator", allocationSize = 10)
    private Long id;

    private String cause;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloseRequest that = (CloseRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cause, that.cause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cause);
    }
}
