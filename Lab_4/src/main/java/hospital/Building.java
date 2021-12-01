package hospital;

import java.util.Objects;

public class Building {
    protected String address;

    public Building(String address) throws IllegalArgumentException {
        if (!address.matches("^[A-Z][, A-Za-z0-9-]{0,255}"))
            throw new IllegalArgumentException("Wrong address");
        this.address = address;
    }

    /**
     * Set building address
     *
     * @param address building address
     */
    public void setAddress(String address) throws IllegalArgumentException {
        if (!address.matches("^[A-Z][, A-Za-z0-9-]{0,255}"))
            throw new IllegalArgumentException("Wrong address");
        this.address = address;
    }

    /**
     * @return building address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Generate hash code for Building
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    /**
     * Generate string from Building object
     *
     * @return string representation of Building
     */
    @Override
    public String toString() {
        return "{\"address\":\"" + address + "\"}";
    }

    /**
     * Compare buildings objects
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
        return Objects.equals(address, ((Building) obj).address);
    }
}
