package hellojpa;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 어플리케이션 로딩 시점에 딱 하나만 만들어야됨
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("kim");

        // 예를 들어 행위를 하고 나가는 경우 db 커넥션을 얻어서 쿼리 날리고 종료할때마다
        // 데이터베이스 커넥션 하나 받았다고 생각하면 된다.
        // 쓰레드간에 공유 X (사용하고 버려야 한다)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        try {

            Team team = new Team();
            team.setName("team");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
            member.setAge(10);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m left join Team t on m.username = t.name", Member.class)
                .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}