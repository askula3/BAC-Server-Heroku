package ba.bac.server.service;

import ba.bac.server.shared.dto.MediaMonitoringDto;
import ba.bac.server.shared.dto.MutualRelationshipsDto;

public interface MediaMonitoringService {
    MediaMonitoringDto getMonitorings(String category);
    MediaMonitoringDto createMonitoring(MediaMonitoringDto monitor);
    MediaMonitoringDto updateMonitoring(MediaMonitoringDto monitor);
    MediaMonitoringDto deleteMonitoring(long id);
}
