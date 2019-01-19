package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * ToDoItemValidationErrorDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-11T03:29:22.478Z")

public class ToDoItemValidationErrorDetails   {
  @JsonProperty("location")
  private String location = null;

  @JsonProperty("param")
  private String param = null;

  @JsonProperty("msg")
  private String msg = null;

  @JsonProperty("value")
  private String value = null;

  public ToDoItemValidationErrorDetails location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public ToDoItemValidationErrorDetails param(String param) {
    this.param = param;
    return this;
  }

  /**
   * Get param
   * @return param
  **/
  @ApiModelProperty(value = "")


  public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  public ToDoItemValidationErrorDetails msg(String msg) {
    this.msg = msg;
    return this;
  }

  /**
   * Get msg
   * @return msg
  **/
  @ApiModelProperty(value = "")


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public ToDoItemValidationErrorDetails value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  **/
  @ApiModelProperty(value = "")


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToDoItemValidationErrorDetails toDoItemValidationErrorDetails = (ToDoItemValidationErrorDetails) o;
    return Objects.equals(this.location, toDoItemValidationErrorDetails.location) &&
        Objects.equals(this.param, toDoItemValidationErrorDetails.param) &&
        Objects.equals(this.msg, toDoItemValidationErrorDetails.msg) &&
        Objects.equals(this.value, toDoItemValidationErrorDetails.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(location, param, msg, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ToDoItemValidationErrorDetails {\n");
    
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    param: ").append(toIndentedString(param)).append("\n");
    sb.append("    msg: ").append(toIndentedString(msg)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

