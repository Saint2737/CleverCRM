package repository;

import java.util.UUID;

import entity.GuestPreferences;

public interface GuestPreferenceRepository extends JpaRepository <GuestPreferences, UUID> {

}
