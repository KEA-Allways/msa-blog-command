package com.allways.domain.reply.repository;

import com.allways.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying
    @Query("update Reply r set r.replyContent = :replyContent where r.replySeq = :replySeq")
    void updateRepliesByReplySeq(Long replySeq, String replyContent);
}
