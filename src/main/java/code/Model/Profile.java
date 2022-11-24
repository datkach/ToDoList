package code.Model;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "profile_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prof_gen")
    @SequenceGenerator(name = "prof_gen", sequenceName = "s_profile", allocationSize = 1)
    private Integer profileId;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "hobby", nullable = false)
    private String hobby;

    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "team")
    private Team team;


    public Profile() {
    }

    public Profile(Integer age, String hobby, String job, String name, String surname, Users user, Team team) {
        this.age = age;
        this.hobby = hobby;
        this.job = job;
        this.name = name;
        this.surname = surname;
        this.user = user;
        this.team = team;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
