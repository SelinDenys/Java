package hospital;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Calendar;
import java.util.Objects;

@JsonDeserialize(builder = Human.Builder.class)
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
    public void setName(String name) {
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
    public String getSurname() {
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
    @JsonPOJOBuilder(buildMethodName = "createHuman", withPrefix = "set")
    public static class Builder {
        protected String name;
        protected String surname;
        protected Calendar birthday;
        protected int id;

        /**
         * Set name
         *
         * @param name human name
         * @return Builder instance
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set surname
         *
         * @param surname human surname
         * @return Builder instance
         */
        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * Check date and set it as day of human birth
         *
         * @param birthday day of human birth
         * @return Builder instance
         */
        public Builder setBirthday(Calendar birthday) {
            this.birthday = birthday;
            return this;
        }

        /**
         * Check number and set it as human id
         *
         * @param id human id
         * @return Builder instance
         */
        public Builder setId(int id) {
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
