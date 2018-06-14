package com.vergjor.android.partyup;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserInfoDao {

    @Query("SELECT COUNT(user_name) FROM user")
    int numberOfUsers();

    @Query("SELECT COUNT(eventTitle) FROM UserSavedEvents")
    int numberOfSavedEvents();

    @Query("SELECT eventTitle, eventDate, eventTime FROM UserReservations")
    List<Events> userReservations();

    @Query("SELECT eventTitle, eventDate, eventTime FROM UserSavedEvents")
    List<Events> userSavedEvents();

    @Query("DELETE FROM UserSavedEvents WHERE eventTitle LIKE :etitle")
    void deleteSavedEvent(String etitle);

    @Query("DELETE FROM UserReservations WHERE eventTitle LIKE :etitle")
    void deleteReservation(String etitle);

    @Query("SELECT user_type FROM user")
    int getUserType();

    @Query("SELECT user_name FROM user")
    String getUserName();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewReservation(UserReservations userReservations);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveEvent(UserSavedEvents userSavedEvents);

}
