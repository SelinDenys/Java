package hospital;

import java.time.LocalDate;
import java.util.Objects;


public class Patient extends Human {
    private String phoneNumber;
    private final int medicalCardId;

    public Patient(Builder builder) {
        super(builder);
        phoneNumber = builder.phoneNumber;
        medicalCardId = builder.medicalCardId;
    }

    /**
     * Set patient phone number
     *
     * @param phoneNumber phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return patient phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return medical card id
     */
    public int getMedicalCardId() {
        return medicalCardId;
    }

    /**
     * Build instance of Patient
     */
    public static class Builder extends Human.Builder {
        protected String phoneNumber;
        protected int medicalCardId;

        /**
         * Check number and set it as patient phone number
         *
         * @param phoneNumber phone number
         * @return Builder instance
         */
        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Check number and set it as patient medical card id
         *
         * @param medicalCardId medical card id
         * @return Builder instance
         */
        public Builder setMedicalCardId(int medicalCardId) {
            this.medicalCardId = medicalCardId;
            return this;
        }

        @Override
        public Builder setName(String name) {
            return (Builder) super.setName(name);
        }

        @Override
        public Builder setSurname(String surname) {
            return (Builder) super.setSurname(surname);
        }

        @Override
        public Builder setBirthday(LocalDate birthday) {
            return (Builder) super.setBirthday(birthday);
        }

        @Override
        public Builder setId(int id) {
            return (Builder) super.setId(id);
        }

        public Patient createPatient() {
            return new Patient(this);
        }
    }

    /**
     * Generate hash code for Patient
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(medicalCardId + ((Human) this).hashCode());
    }

    /**
     * Generate string from Patient object
     *
     * @return string representation of Patient
     */
    @Override
    public String toString() {
        return "{name: " + name + ", surname: " + surname + ", birthday: " + birthday + ", ID: " + id + ", phone number: "
                + phoneNumber + ", medical card id: " + medicalCardId + "}";
    }

    /**
     * Compare patients objects
     *
     * @param obj object to compare
     * @return are two objects equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        if (this == obj)
            return true;
        return Objects.equals(name, ((Patient) obj).name)
                && Objects.equals(surname, ((Patient) obj).surname)
                && Objects.equals(birthday, ((Patient) obj).birthday)
                && Objects.equals(id, ((Patient) obj).id)
                && Objects.equals(phoneNumber, ((Patient) obj).phoneNumber)
                && Objects.equals(medicalCardId, ((Patient) obj).medicalCardId);
    }
}
