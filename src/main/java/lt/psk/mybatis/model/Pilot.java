package lt.psk.mybatis.model;

public class Pilot {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PILOT.ID
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PILOT.AGE
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    private String age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PILOT.NAME
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PILOT.PERSON_ID
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    private Integer personId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PILOT.ID
     *
     * @return the value of PUBLIC.PILOT.ID
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PILOT.ID
     *
     * @param id the value for PUBLIC.PILOT.ID
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PILOT.AGE
     *
     * @return the value of PUBLIC.PILOT.AGE
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    public String getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PILOT.AGE
     *
     * @param age the value for PUBLIC.PILOT.AGE
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PILOT.NAME
     *
     * @return the value of PUBLIC.PILOT.NAME
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PILOT.NAME
     *
     * @param name the value for PUBLIC.PILOT.NAME
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PILOT.PERSON_ID
     *
     * @return the value of PUBLIC.PILOT.PERSON_ID
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    public Integer getPersonId() {
        return personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PILOT.PERSON_ID
     *
     * @param personId the value for PUBLIC.PILOT.PERSON_ID
     *
     * @mbg.generated Tue Apr 06 23:37:19 EEST 2021
     */
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}