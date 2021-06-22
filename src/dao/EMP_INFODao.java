package dao;

import java.util.List;

public interface EMP_INFODao {
	
	List<String> getMailID(int plt, String sec, String application);

}
