package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ToDoItemValidationErrorDetails;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * ToDoItemValidationError
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-11T03:29:22.478Z")

public class ToDoItemValidationError   {
  @JsonProperty("details")
  @Valid
  private List<ToDoItemValidationErrorDetails> details = null;

  @JsonProperty("name")
  private String name = null;

  public ToDoItemValidationError details(List<ToDoItemValidationErrorDetails> details) {
    this.details = details;
    return this;
  }

  public ToDoItemValidationError addDetailsItem(ToDoItemValidationErrorDetails detailsItem) {
    if (this.details == null) {
      this.details = new ArrayList<ToDoItemValidationErrorDetails>();
    }
    this.details.add(detailsItem);
    return this;
  }

  /**
   * Get details
   * @return details
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ToDoItemValidationErrorDetails> getDetails() {
    return details;
  }

  public void setDetails(List<ToDoItemValidationErrorDetails> details) {
    this.details = details;
  }

  public ToDoItemValidationError name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "ValidationError", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToDoItemValidationError toDoItemValidationError = (ToDoItemValidationError) o;
    return Objects.equals(this.details, toDoItemValidationError.details) &&
        Objects.equals(this.name, toDoItemValidationError.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(details, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ToDoItemValidationError {\n");
    
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
}

