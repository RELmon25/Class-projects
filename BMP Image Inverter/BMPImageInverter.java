import javafx.event.*;

public class BMPImageInverter extends Application
{
	@Override
	public void start(Stage stage)
    {
		stage.setTitle("Invertidor de im\u00E1genes .bmp");
		
		
		
		//FIRST SCENE
		
		VBox vbox = new VBox();
		HBox hbox = new HBox();
		
		VBox allControls = new VBox(5);
		
		Label nda = new Label("Nombre del archivo");
		nda.setFont(Font.font("Tahoma", 20));
		nda.setTextFill(Color.WHITE);
  
        HBox middleControls = new HBox(5);
		
		TextField name = new TextField();
		name.setFont(Font.font("Tahoma", 18));
		
		Label bmp = new Label(".bmp  ");
		bmp.setFont(Font.font("Tahoma", 24));
		bmp.setTextFill(Color.WHITE);
		
		Button invertir = new Button ("Invertir");
		invertir.setFont(Font.font("Tahoma", 18));
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Archivo no encontrado");
		alert.setContentText("Ingrese el nombre de un archivo .bmp existente.");
		
		middleControls.getChildren().addAll(
			name, 
			bmp, 
			invertir
		);
		
		allControls.getChildren().addAll(
			nda,
			middleControls,
			new Label("")
		);
		
		vbox.setAlignment(Pos.CENTER);
		hbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(allControls);
		hbox.getChildren().add(vbox);
		
		middleControls.setStyle("-fx-background-color: transparent");
		allControls.setStyle("-fx-background-color: transparent");
		vbox.setStyle("-fx-background-color: transparent");
		hbox.setStyle("-fx-background-color: transparent");

        Scene scene1 = new Scene(hbox, 600, 300);
		scene1.setFill(new LinearGradient(
				0, 0, 1, 1, true,                     
				CycleMethod.NO_CYCLE,                  
				new Stop(0, Color.web("#736efe")),    
				new Stop(1, Color.web("#5efce8")))
		);
		
		
		
		
		//SECOND SCENE
		
		VBox originalImageBox = new VBox(5);
		
		ImageView originalImage = new ImageView();
		Label imOr = new Label("Imagen Original");
		imOr.setFont(Font.font("Tahoma", 20));
		imOr.setTextFill(Color.WHITE);
		
		originalImageBox.getChildren().addAll(
			imOr,
			originalImage
		);
		
		VBox invertedImageBox = new VBox(5);
		
		ImageView invertedImage = new ImageView();
		Label imIn = new Label("Imagen Invertida");
		imIn.setFont(Font.font("Tahoma", 20));
		imIn.setTextFill(Color.BLACK);
		
		invertedImageBox.getChildren().addAll(
			imIn,
			invertedImage
		);
		
		HBox images = new HBox(20);
		images.getChildren().addAll(
			originalImageBox,
			invertedImageBox
		);
		
		AnchorPane ap = new AnchorPane();
		
		ap.getChildren().add( images );

        AnchorPane.setTopAnchor( images, 20.0d );
        AnchorPane.setBottomAnchor( images, 70.0d );
        AnchorPane.setRightAnchor( images, 20.0d );
        AnchorPane.setLeftAnchor( images, 20.0d );
		
		Button regresar = new Button("Regresar");
		regresar.setFont(Font.font("Tahoma", 18));
		
		ap.getChildren().add( regresar );
		
		AnchorPane.setBottomAnchor( regresar, 20.0d );
        AnchorPane.setRightAnchor( regresar, 20.0d );
		
		originalImageBox.setStyle("-fx-background-color: transparent");
		invertedImageBox.setStyle("-fx-background-color: transparent");
		images.setStyle("-fx-background-color: transparent");
		ap.setStyle("-fx-background-color: transparent");

		Scene scene2 = new Scene(ap);
		scene2.setFill(new LinearGradient(
				0, 1, 1, 0, true,                      
				CycleMethod.NO_CYCLE,                  
				new Stop(0, Color.web("#f6416c")),     
				new Stop(1, Color.web("#fff6b7")))
		);
		
		
		
		
		
		//EVENTS
		
		invertir.setOnAction(value ->  {
			
			String s = name.getText();
			
			try
			{
				RandomAccessFile in = new RandomAccessFile(s+".bmp","r");
				RandomAccessFile out = new RandomAccessFile(s+"_Invertido.bmp","rw");
				
				int len = (int) in.length();
				byte copia[] = new byte[len];
				
				in.read(copia);
				
				for(int i=54;i<len;i++){
					copia[i] = (byte) (255 - (int)copia[i]) ;
				}

				out.write(copia);
				
				out.close();
				in.close();
				
				originalImage.setImage(new Image( getClass().getResourceAsStream(s+".bmp") ) );
				invertedImage.setImage(new Image( getClass().getResourceAsStream(s+"_Invertido.bmp" ) ) );

				
				stage.setScene(scene2);
				stage.show();
			}
			catch(FileNotFoundException ex)
			{
				alert.showAndWait();
			}
			catch(Exception ex2){
				ex2.printStackTrace();
			}
			
		});
		
		regresar.setOnAction(value ->  {
			
			name.setText("");
			
			stage.setScene(scene1);
			stage.show();
			
		});
		
        stage.setScene(scene1);
        stage.show();
    }
  
	public static void main(String[] args)
	{
        launch(args);
    }
}
