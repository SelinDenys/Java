package hospital;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor extends Human {
    private String phoneNumber;
    private final List<Patient> patients = new ArrayList<>();
    private int cabinetNumber;
    private int salary;

    public Doctor(Builder builder) {
        super(builder);
        phoneNumber = builder.phoneNumber;
        patients.addAll(builder.patients);
        cabinetNumber = builder.cabinetNumber;
        salary = builder.salary;
    }

    /**
     * Set doctor phone number
     *
     * @param phoneNumber phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Add patient to doctor's patient list
     *
     * @param patient patient whom need to add
     */
    public void setPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Add patients to doctor's patient list
     *
     * @param patients patients whoms need to add
     */
    public void setPatients(List<Patient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Return all doctor's patient
     *
     * @return list of doctor's patient
     */
    public List<Patient> getAllPatients() {
        return patients;
    }

    /**
     * Set doctor cabinet number
     *
     * @param cabinetNumber cabinet number
     */
    public void setCabinetNumber(int cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    /**
     * @return cabinet number
     */
    public int getCabinetNumber() {
        return cabinetNumber;
    }

    /**
     * Set doctor salary
     *
     * @param salary salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * @return doctor salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Build instance of Doctor
     */
    public static class Builder extends Human.Builder {
        private String phoneNumber;
        private final List<Patient> patients = new ArrayList<>();
        private int cabinetNumber;
        private int salary;

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
         * Set list of doctor's patient
         *
         * @param patients list of patients
         * @return Builder instance
         */
        public Builder setPatients(List<Patient> patients) {
            this.patients.addAll(patients);
            return this;
        }

        /**
         * Check number and set it as doctor cabinet number
         *
         * @param cabinetNumber cabinet number
         * @return Builder instance
         */
        public Builder setCabinetNumber(int cabinetNumber) {
            this.cabinetNumber = cabinetNumber;
            return this;
        }

        /**
         * Check number and set it as doctor salary
         *
         * @param salary salary
         * @return Builder instance
         */
        public Builder setSalary(int salary) {
            this.salary = salary;
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

        public Doctor createDoctor() {
            return new Doctor(this);
        }
    }

    /**
     * Generate hash code for Doctor
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(cabinetNumber + ((Human) this).hashCode());
    }

    /**
     * Generate string from Doctor object
     *
     * @return string representation of Doctor
     */
    @Override
    public String toString() {
        return "{name: " + name + ", surname: " + surname + ", birthday: " + birthday + ", ID: " + id + ", phone number: "
                + phoneNumber + ", patients: " + patients + ", cabinet number: " + cabinetNumber + ", salary: "
                + salary + "}";
    }

    /**
     * Compare doctors objects
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
        return Objects.equals(name, ((Doctor) obj).name)
                && Objects.equals(surname, ((Doctor) obj).surname)
                && Objects.equals(birthday, ((Doctor) obj).birthday)
                && Objects.equals(id, ((Doctor) obj).id)
                && Objects.equals(phoneNumber, ((Doctor) obj).phoneNumber)
                && Objects.equals(cabinetNumber, ((Doctor) obj).cabinetNumber)
                && Objects.equals(salary, ((Doctor) obj).salary)
                && Objects.equals(patients, ((Doctor) obj).patients);
    }
}
