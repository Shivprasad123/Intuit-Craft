package com.intuit.commentService.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationData{
    Long total;
    int pageNo;
    int pageSize;

    //TODO: Explore generics
    public static PaginationData mapToPageData(){
        return PaginationData.builder()
                .build();
    }
}
