/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.courrier.service.impl;
import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
/**
 *
 * @author HP
 */
@Service
public class ReportGeneratorService {
   Logger logger = LogManager.getLogger(ReportGeneratorService.class);
    public byte[] genererRapport(InputStream fichier, HashMap<String, ? super Object> parametres,
                                 JRDataSource jsonDataSource, Boolean typeSource) {
        byte[] fluxFichier = null;
        JasperPrint jasperPrint;
        try {
            //Compile la source du fichier
                //Execution du rapport
                if(typeSource){
                JasperReport jasperReport = JasperCompileManager.compileReport(fichier);
                jasperPrint = JasperFillManager.fillReport(jasperReport, parametres, jsonDataSource);
                }else{
                 jasperPrint = JasperFillManager.fillReport(fichier, parametres, jsonDataSource);
                }
                fluxFichier = exportToPDF(jasperPrint);
                logger.info("le byte est"+ fluxFichier);
        } catch (JRException ex) {
            logger.info(ex.getMessage());
        }
        return fluxFichier;
    }
    
    
     
}
