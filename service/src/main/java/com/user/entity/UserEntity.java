package com.user.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email"),
				@UniqueConstraint(columnNames = "phone")
		},
		indexes = {
				@Index(name = "idx_email", columnList = "email"),
				@Index(name = "idx_phone", columnList = "phone")
		})
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String username;

	@Column(name = "nickname", nullable = false, length = 50, columnDefinition = "VARCHAR(50) DEFAULT '' COMMENT '昵称'")
	private String nickname = "";

	@Column(name = "password_hash", nullable = false, length = 255)
	private String passwordHash;

	@Column(nullable = false, length = 150)
	private String email = "";

	@Column(nullable = false, length = 20)
	private String phone = "";

	@Column(nullable = false)
	private Integer status = 0;

	@CreationTimestamp
	@Column(name = "create_time", updatable = false, columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)")
	private LocalDateTime createTime;

	@UpdateTimestamp
	@Column(name = "update_time", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)")
	private LocalDateTime updateTime;
}
