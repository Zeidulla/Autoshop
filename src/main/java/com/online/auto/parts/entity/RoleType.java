package com.online.auto.parts.entity;

public enum RoleType {
    CUSTOMER("CUSTOMER", "customer"), SELLER("SELLER", "seller");

    String roleName;
    String url;

    RoleType(String roleName, String url) {
        this.roleName = roleName;
        this.url = url;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getUrl() {
        return url;
    }

    public Role getRole() {
        return new Role(roleName);
    }

}
