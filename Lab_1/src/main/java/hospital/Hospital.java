package hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hospital extends Building {
    private int number;
    private final List<Doctor> doctors = new ArrayList<>();

    public Hospital(String address, int number, List<Doctor> doctors) {
        super(address);
        this.number = number;
        this.doctors.addAll(doctors);
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
     * Erase doctor from hospital's doctor list
     *
     * @param doctor doctor whom need to erase
     */
    public void eraseDoctor(Doctor doctor) {
        doctors.remove(doctor);
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
    public List<Doctor> getAllDoctors() {
        return doctors;
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
        return "{" + super.toString() + ", number: " + number + ", doctors: " + doctors + "}";
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
