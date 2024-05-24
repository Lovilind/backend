package com.project.lovlind.domain.chat.controller.dto.request;

import java.util.List;
import lombok.Builder;

@Builder
public record PostChatDto(
    int maxParticipant, int minParticipant, String title, List<HobbyDto> hobbyDtoList) {}
