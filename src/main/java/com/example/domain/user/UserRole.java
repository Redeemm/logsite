package com.example.domain.user;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER(Sets.newHashSet());

    private final Set<UserPermission> permissions;
}
