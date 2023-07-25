module BloodBankAssistant {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens donormaster to javafx.graphics, javafx.fxml;
	opens unitcollector to javafx.graphics, javafx.fxml;
	opens login to javafx.graphics, javafx.fxml;
	opens controlpanel to javafx.graphics, javafx.fxml;
	opens stock to javafx.graphics, javafx.fxml;
	opens issueblood to javafx.graphics, javafx.fxml;
	opens exploredonors to javafx.graphics, javafx.fxml,javafx.base;
	opens bloodcollectiontblview to javafx.graphics, javafx.fxml,javafx.base;
	opens bloodissuedview to javafx.graphics, javafx.fxml,javafx.base;
	opens meetthedeveloper to javafx.graphics, javafx.fxml;
	opens historypanel to javafx.graphics, javafx.fxml;
}
