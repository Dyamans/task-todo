package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;


import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ToDoItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-11T03:29:22.478Z")
@Entity
@Table(name = "toDoItem")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@DynamicUpdate
public class ToDoItem {
	
  @JsonProperty("id")
  @GeneratedValue(
	strategy= GenerationType.AUTO,
	generator="native"
  )
  @GenericGenerator(
     name = "native",
     strategy = "native"
  )
  @Id
  @NotNull(groups = Existing.class)
  @Null(groups = New.class)
  @ApiModelProperty(example = "42", value = "", position = 0)  
  private Integer id = null;

  @JsonProperty("text")
  @Column  
  @NotNull(groups = {Existing.class, New.class})
  @Length(
          min=1,
          max=50,
          message = "text must between 1 and 50 characters"         
  )
  @ApiModelProperty(example = "Are you done with atuo assingment task.", required = true, value = "", position = 1)
  private String text = null;

  @JsonProperty("isCompleted")
  @Column
  @ApiModelProperty(example = "false", value = "", position = 2)
  private Boolean isCompleted = null;
  

  @JsonProperty("createdAt")
  @Column
  @ApiModelProperty(value = "", position = 3)
  private String createdAt = null;
  
  public ToDoItem() {
  }
  
  public ToDoItem id(Integer id) {
	    this.id = id;
	    return this;
  }

  public ToDoItem(int id, String text, boolean isCompleted, String createdAt) {
      this.id = id;
      this.text = text;
      this.isCompleted = isCompleted;
      this.createdAt = createdAt;
  }

 
  
  public ToDoItem(String text, boolean isCompleted, String createdAt) {
      this.text = text;
      this.isCompleted = isCompleted;
      this.createdAt = createdAt;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "42.0", value = "")

  @Valid

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ToDoItem text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
  **/
  @ApiModelProperty(example = "Uulwi ifis halahs gag erh'ongg w'ssh.", value = "")


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public ToDoItem isCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
    return this;
  }

  /**
   * Get isCompleted
   * @return isCompleted
  **/
  @ApiModelProperty(example = "false", value = "")


  public Boolean isIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

  public ToDoItem createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(example = "2017-10-13T01:50:58.735Z", value = "")


  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToDoItem toDoItem = (ToDoItem) o;
    return Objects.equals(this.id, toDoItem.id) &&
        Objects.equals(this.text, toDoItem.text) &&
        Objects.equals(this.isCompleted, toDoItem.isCompleted) &&
        Objects.equals(this.createdAt, toDoItem.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, text, isCompleted, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ToDoItem {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    isCompleted: ").append(toIndentedString(isCompleted)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }  
  
  public interface Existing {
  }

  public interface New {
  }


}

