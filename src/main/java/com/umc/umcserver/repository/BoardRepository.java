package com.umc.umcserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b Where b.title = :title")
    List<Board> findByTitle(@Param("title") String title);
}
