
public interface SettingsRepository extends JpaRepository<Settings, Integer>{
	
	List<key> getAllSettingsByKeys();
	String getSettingKey(String key);
	String getSettingValue(String value);
	String getSettingDescription(String description);
	

}
