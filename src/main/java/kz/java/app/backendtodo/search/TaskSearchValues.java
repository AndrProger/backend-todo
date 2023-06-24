package kz.java.app.backendtodo.search;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class TaskSearchValues {
    String text;
    Integer completed;
    Long priorityId;
    Long categoryId;
}
