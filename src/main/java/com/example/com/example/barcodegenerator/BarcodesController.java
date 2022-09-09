package com.example.com.example.barcodegenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/barcodes")
public class BarcodesController {

    @GetMapping(value = "/zxing/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zXingEAN13Barcode(@PathVariable("barcode") String barcode){
        return okResponse(CodeGenerator.generateEAN13BarCodeImg(barcode));
    }

    @GetMapping(value = "/zxing/qrcode/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zXingQRBarcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(CodeGenerator.generateQRCodeImg(barcode));
    }

    private ResponseEntity<BufferedImage> okResponse(BufferedImage img) {
        return new ResponseEntity<>(img, HttpStatus.OK);
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttMessageConverter(){
        return new BufferedImageHttpMessageConverter();
    }


}
