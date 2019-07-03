package net.empirechaotix.shift.accountservice.model;

import lombok.Data;
import net.empirechaotix.shift.accountservice.dao.model.User;

import java.util.List;

@Data
public class Wrapper {

    private List<User> users;

}
