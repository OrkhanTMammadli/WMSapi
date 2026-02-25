package com.ltc.tableservice.mapper;

import com.ltc.tableservice.dto.TableRequestDTO;
import com.ltc.tableservice.dto.TableResponseDTO;
import com.ltc.tableservice.entity.WeddingTable;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface TableMapper {
    TableResponseDTO toTableResponseDTO (WeddingTable weddingTable);
    WeddingTable toWeddingTable (TableRequestDTO tableRequestDTO);
    void updateWeddingTable(TableRequestDTO tableRequestDTO, @MappingTarget WeddingTable weddingTable);

}
