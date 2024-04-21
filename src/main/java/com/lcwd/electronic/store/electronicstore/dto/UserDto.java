package com.lcwd.electronic.store.electronicstore.dto;

import com.lcwd.electronic.store.electronicstore.validate.ImageNameValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {


    private String userId;

    @Size(min=3, max=10,message="Invalid user name !!")
    private String name;

//    @Email(message="Invalid user email..")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Invalid Email")
    private String email;
    @NotBlank
    private String password;
    @Size(min=4,max=6,message = "Invalid gender")
    private String gender;

    @NotBlank(message = "write something..")   
    private String about;

    @ImageNameValid
    private String imageName;
}
