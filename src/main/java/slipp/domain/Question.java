package slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	Long id;

	@ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
//	@Column(name="writer", nullable=false, length=25)
	User writer;

//	@Column(name="userPk", nullable=false, length=25)
//	Long userPk;
	
	@Column(name="title")
	String title;
	@Column(name="contents")
	String contents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
