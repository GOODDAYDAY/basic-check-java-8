package com.goody.utils.basiccheck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * Data Transfer Object for Bean Validation testing
 * Contains nested validation annotations for comprehensive testing
 *
 * @author Goody
 * @version 1.0, 2023/4/12 11:32
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidDTO {
    @Negative
    @NotNull
    private Integer id;
    @NotBlank
    private String name;
    @NotEmpty
    private List<@Valid @NotNull ValidDTO1> list;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ValidDTO1 {
        @Positive
        @NotNull
        private Integer age;
    }
}
