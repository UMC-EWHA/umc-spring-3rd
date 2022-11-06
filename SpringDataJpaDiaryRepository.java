package cje.SymDaDiary.repository;

import cje.SymDaDiary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/*
* JpaRepository 상속 -> 자동으로 빈 등록 (@Repository 안 달아도 됨)
* */
public interface SpringDataJpaDiaryRepository extends JpaRepository<Diary, Long>, DiaryRepository  {
    @Override
    List<Diary> findByMonth(String month);  // JPQL select m from Diary m where m.month=?

    @Override
    Optional<Diary> findByDate(String date);

    @Override
    void deleteByDate(String date);

}
