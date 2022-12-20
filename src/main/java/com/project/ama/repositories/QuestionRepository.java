package com.project.ama.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.ama.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query(value = "select * from question where to_whom_user_id = :toWhomUserId",
			nativeQuery = true)
	List<Question> findByToWhomUserId(@Param("toWhomUserId") Long toWhomUserId);

}
