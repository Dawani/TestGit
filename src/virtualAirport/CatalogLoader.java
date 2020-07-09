package virtualAirport;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CatalogLoader {
	//有关于接口类中含有的方法属性问题？
	abstract Catalog loadCatalog(String fileName)
			throws FileNotFoundException, IOException, DataFormatException;
}
