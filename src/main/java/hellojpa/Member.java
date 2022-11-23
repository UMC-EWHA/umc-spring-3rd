package hellojpa;
import javax.persistence.*;
import java.util.Date;


@Entity
public class Member {

 @Id @GeneratedValue
 @Column(name = "MEMBER_ID")
 private String id;

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 @Column(name = "USERNAME")
 private String username;

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }



 //@Column(name = "TEAM_ID")
 //
 //private Long teamId;

 @ManyToOne
 @JoinColumn(name ="Team_ID")
 private Team team;

 public Team getTeam() {
  return team;
 }

 public void setTeam(Team team) {
  this.team = team;
 }
 @Column(name = "AGE")
 private int age;
 public void setAge(int age) {
  this.age = age;
 }

 public int getAge() {
  return age;
 }
//public Long getTeamId() {
 // return teamId;
// }

 //public void setTeamId(Long teamId) {
 // this.teamId = teamId;
 //}


 @Override
 public String toString() {
  return "Member{" +
          "id='" + id + '\'' +
          ", username='" + username + '\'' +
          ", age=" + age +
          '}';
 }
}
