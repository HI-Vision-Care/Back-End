package com.hivision.hivision.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hivision.hivision.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class Account implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "AccountID")
    String id;
    String username;
    @JsonIgnore
    String password;
    String email;
    String phone;
    String avatar;

    @Enumerated(EnumType.STRING)
    Role role;

    @Column(name = "Is_Deleted")
    Boolean isDeleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Authority chính là "quyền" mà @PreAuthorize sẽ kiểm tra
        // Bạn có thể trả về một danh sách các quyền
        // Thông thường, người ta hay gán role làm authority
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
//    @JsonIgnore
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() { // getAuthorities: lấy danh sách quyền
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        if (this.role != null) {
//            authorities.add(new SimpleGrantedAuthority(this.role.toString()));
//        }
//        return authorities;
//    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
