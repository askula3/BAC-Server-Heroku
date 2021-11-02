package ba.bac.server.service;

import ba.bac.server.shared.dto.MutualRelationshipsDto;

public interface MutualRelationshipsService {
    MutualRelationshipsDto getMutualRelationshipsByCountry(String country);
    MutualRelationshipsDto updateMutualRelationships(String country, MutualRelationshipsDto mutualRelationshipsDto);
}
