package virtualAirport;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CatalogLoader {
	//�й��ڽӿ����к��еķ����������⣿
	abstract Catalog loadCatalog(String fileName)
			throws FileNotFoundException, IOException, DataFormatException;
}
