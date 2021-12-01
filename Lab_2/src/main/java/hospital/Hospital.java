package hospital;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = Hospital.Builder.class)
public class Hospital extends Building {
    private int number;
    private final List<Doctor> doctors = new ArrayList<>();

    public Hospital(Builder builder) {
        super(builder.address);
        this.number = builder.number;
        this.doctors.addAll(builder.doctors);
    }

    /**
     * Set hospital number
     *
     * @param number hospital number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return hospital number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Add doctor to hospital's doctor list
     *
     * @param doctor doctor whom need to add
     */
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    /**
     * Add doctors to hospital's doctor list
     *
     * @param doctors doctors whoms need to add
     */
    public void addDoctors(List<Doctor> doctors) {
        this.doctors.addAll(doctors);
    }

    /**
     * Get all doctors from hospital's doctor list
     *
     * @return list of doctors
     */
    public List<Doctor> getDoctors() {
        return doctors;
    }

    @JsonPOJOBuilder(buildMethodName = "createHospital", withPrefix = "set")
    public static class Builder {
        private String address;
        private int number;
        private final List<Doctor> doctors = new ArrayList<>();

        /**
         * Set address of hospital
         *
         * @param address hospital address
         * @return Builder instance
         */
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * Set number of hospital
         *
         * @param number hospital number
         * @return Builder instance
         */
        public Builder setNumber(int number) {
            this.number = number;
            return this;
        }

        /**
         * Set list of hospital doctors
         *
         * @param doctors list of doctors
         * @return Builder instance
         */
        public Builder setDoctors(List<Doctor> doctors) {
            this.doctors.addAll(doctors);
            return this;
        }

        /**
         * Create new Hospital object
         *
         * @return Hospital instance
         */
        public Hospital createHospital() {
            return new Hospital(this);
        }
    }

    /**
     * Generate hash code for Hospital
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(number + super.hashCode());
    }

    /**
     * Generate string from Hospital object
     *
     * @return string representation of Hospital
     */
    @Override
    public String toString() {
        return "{\"address\":\"" + address + "\",\"number\":\"" + number + "\",\"doctors\":" + doctors + "}";
    }

    /**
     * Compare hospitals objects
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
        return Objects.equals(address, ((Hospital) obj).address)
                && Objects.equals(number, ((Hospital) obj).number)
                && Objects.equals(doctors, ((Hospital) obj).doctors);
    }
}
