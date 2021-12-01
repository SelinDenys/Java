package hospital;

import java.util.Calendar;
import java.util.Objects;

import javax.validation.constraints.*;

public class Human {
    protected String name;
    protected String surname;
    protected Calendar birthday;
    protected final int id;

    public Human(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.birthday = builder.birthday;
        this.id = builder.id;
    }

    /**
     * Set name
     *
     * @param name new name
     */
    public void setName(String name) throws IllegalArgumentException {
        if (!name.matches("^[A-Z][a-z0-9]{0,31}"))
            throw new IllegalArgumentException("Wrong name");
        this.name = name;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set surname
     *
     * @param surname new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return surname
     */
    public String getSurname() throws IllegalArgumentException {
        if (!surname.matches("^[A-Z][a-z0-9]{0,31}"))
            throw new IllegalArgumentException("Wrong surname");
        return surname;
    }

    /**
     * @return date of birth
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Build instance of Human
     */
    public static class Builder {
        @Size(min = 1, max = 32, message = "Too short or too long name")
        protected String name;
        @Size(min = 1, max = 32, message = "Too short or too long surname")
        protected String surname;
        @PastOrPresent(message = "Human must be born")
        protected Calendar birthday;
        @Positive(message = "Id must be greater than zero")
        protected int id;

        /**
         * Set name
         *
         * @param name human name
         * @return Builder instance
         */
        public Builder setName(String name) throws IllegalArgumentException {
            if (!name.matches("^[A-Z][a-z0-9]{0,31}"))
                throw new IllegalArgumentException("Wrong name");
            this.name = name;
            return this;
        }

        /**
         * Set surname
         *
         * @param surname human surname
         * @return Builder instance
         */
        public Builder setSurname(String surname) throws IllegalArgumentException {
            if (!surname.matches("^[A-Z][a-z0-9]{0,31}"))
                throw new IllegalArgumentException("Wrong surname");
            this.surname = surname;
            return this;
        }

        /**
         * Check date and set it as day of human birth
         *
         * @param birthday day of human birth
         * @return Builder instance
         * @throws IllegalArgumentException if year of human birth less than 1850
         */
        public Builder setBirthday(Calendar birthday) throws IllegalArgumentException {
            if (birthday.get(Calendar.YEAR) < 1850)
                throw new IllegalArgumentException("Wrong birthday. Human too old");
            if (Calendar.getInstance().compareTo(birthday) < 0)
                throw new IllegalArgumentException("Wrong birthday. Human too young");
            this.birthday = birthday;
            return this;
        }

        /**
         * Check number and set it as human id
         *
         * @param id human id
         * @return Builder instance
         * @throws IllegalArgumentException if id less than zero
         */
        public Builder setId(int id) throws IllegalArgumentException {
            if (id < 0)
                throw new IllegalArgumentException("Wrong id");
            this.id = id;
            return this;
        }

        /**
         * Create new Human object
         *
         * @return Human instance
         */
        public Human createHuman() {
            return new Human(this);
        }
    }

    /**
     * Generate hash code for Human
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Generate string from Human object
     *
     * @return string representation of Human
     */
    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\",\"surname\":\"" + surname + "\",\"birthday\":\"" + birthday.getTimeInMillis() + "\",\"id\":\"" + id + "\"}";
    }

    /**
     * Compare humans objects
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
        return Objects.equals(name, ((Human) obj).name) && Objects.equals(surname, ((Human) obj).surname)
                && Objects.equals(id, ((Human) obj).id);
    }
}
