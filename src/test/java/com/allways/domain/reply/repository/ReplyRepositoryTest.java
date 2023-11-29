package com.allways.domain.reply.repository;

import com.allways.common.factory.reply.ReplyFactory;
import com.allways.domain.reply.entity.Reply;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
public class ReplyRepositoryTest {
    @Autowired private ReplyRepository replyRepository;
    @Autowired private EntityManager entityManager;

    @Transactional
    @Test
    public void updateByReplySeqTest() {
        // Given
        String newContent = "Updated reply content";

        Reply reply = ReplyFactory.createReply();
        Reply makeReply = replyRepository.save(reply);

        entityManager.flush();
        entityManager.clear();

        Long replySeq = makeReply.getReplySeq();

        // When
        replyRepository.updateRepliesByReplySeq(replySeq, newContent);

        // Then
        Reply updatedReply = replyRepository.findById(replySeq).orElse(null);
        assertThat(updatedReply).isNotNull();
        assertThat(updatedReply.getReplyContent()).isEqualTo(newContent);

        replyRepository.deleteById(replySeq);
    }
}
