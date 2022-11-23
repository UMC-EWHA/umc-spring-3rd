package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();//트랜잭션 시작

        try {
            //tx.begin(); //트랜잭션 시작
            logic(em); //비지니스 로직 실행
            for(int i=0;i<100;i++) {
                Member member = new Member();
                member.setUsername("member1"+i);
                member.setAge(i);
                em.persist(member);
            }
            em.flush();
            em.clear();


           List<Member> result =  em.createQuery("select m from Member m order by m.age desc", Member.class)
                            .setFirstResult(1)
                            .setMaxResults(10)
                            .getResultList();
            System.out.println("result = "+ result.size());
            for(Member member1 : result){
                System.out.println("member1 ="+member1);
            }


            tx.commit(); //트랜잭션 커밋
          /**  //저장
            Team team = new Team();
            team.setName("TeamA");
           // team.getMembers().add(member);
            em.persist(team);



            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);





            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for(Member m:members){
                System.out.println("m= "+m.getUsername());
            }
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = "+ findTeam.getName());**/

        } catch (Exception e){
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close();
        }

        emf.close();

    }

    private static void logic(EntityManager em){
        String id ="id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("윤진");
        member.setAge(2);

        em.persist(member);


        member.setAge(20);
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember="+findMember.getUsername()+", age="+ findMember.getAge());


        em.remove(member);



    }












}
