package com.example.demo.user.web.rest.errors;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldErrorVM implements Serializable {
  private static final long serialVersionUID = 5885275907744758191L;

  private final String objectName;
  private final String field;
  private final String message;
}
