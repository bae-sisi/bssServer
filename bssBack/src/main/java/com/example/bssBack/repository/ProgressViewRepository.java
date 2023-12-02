package com.example.bssBack.repository;

import com.example.bssBack.entity.ProgressView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgressViewRepository extends JpaRepository<ProgressView, Long> {

    @Query(nativeQuery = true, value = "select * from progress_view as pv;")
    List<ProgressView> FindAllProgress();

    @Query(nativeQuery = true, value = "select * from progress_view as pv where pv.lecture_Name like %:index% ;")
    List<ProgressView> FindConsistIndexProgress(@Param("index") String index);

    @Query(nativeQuery = true, value = "select * from progress_view as pv where pv.grade = :grade ;")
    List<ProgressView> FindProgressesByGrade(@Param("grade") Integer grade);

    @Query(nativeQuery = true, value = "select * from progress_view as pv where pv.lecture_Name like %:index% and pv.grade = :grade ;")
    List<ProgressView> FindProgressesByGradeANDConsistIndex(@Param("grade") Integer grade, @Param("index") String index);

    @Query(nativeQuery = true, value = "SELECT CASE WHEN EXISTS ( SELECT 1 FROM progress AS pr WHERE pr.Pid = (SELECT p.Pid FROM professor AS p WHERE p.name = :prof)" +

            "AND pr.Lid = (SELECT l.lid FROM lecture AS l WHERE l.name = :lecture) )" +
            "THEN (select pr.id from progress as pr WHERE pr.Pid = (SELECT p.Pid FROM professor AS p WHERE p.name = :prof)" +
            "AND pr.Lid = (SELECT l.lid FROM lecture AS l WHERE l.name = :lecture) )ELSE 0 END AS Result;")
    Long IsExistsProgress(@Param("prof") String prof, @Param("lecture") String lecture);

    @Query(nativeQuery = true, value = "SELECT * from progress_view as pv where pv.year = :year ;")
    List<ProgressView> FindProgreeseByYear(@Param("year")Integer year);
}
