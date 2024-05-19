package com.ohgiraffers.dosirak.admin.product.controller;

import com.ohgiraffers.dosirak.admin.login.model.AdminLoginDetails;
import com.ohgiraffers.dosirak.admin.product.dto.ProductImageDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productAddImageDTO;
import com.ohgiraffers.dosirak.admin.product.dto.productDTO;
import com.ohgiraffers.dosirak.admin.product.service.ProductService;
import com.ohgiraffers.dosirak.common.product.ProductUpdateException;
import com.ohgiraffers.dosirak.user.customer.model.dto.UserAskDTO;
import com.ohgiraffers.dosirak.user.customer.model.dto.UserCustomerImgDTO;
import com.ohgiraffers.dosirak.user.customer.model.service.UserCustomerService;
import com.ohgiraffers.dosirak.user.login.model.dto.LoginDTO;
import com.ohgiraffers.dosirak.user.product.dto.ProductandImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@Slf4j
@RequestMapping("/admin/product/*")
public class ProductController {

    private final ProductService productService;
    private final UserCustomerService userCustomerService;

    @Value("${image.product-image-dir}")
    private String IMAGE_DIR;


    @Autowired
    public ProductController(ProductService productService, UserCustomerService userCustomerService) {
        this.productService = productService;
        this.userCustomerService = userCustomerService;
    }

    @GetMapping("/productList")
    public String productList(Model model) {
        List<productDTO> productList = productService.findAllProduct();
        model.addAttribute("productList", productList);
        return "/admin/product/productList";
    }

    @PostMapping("/product/search")
    public String productSelectList(@RequestParam String key, Model model) {
        List<productDTO> productList = productService.productSelectList(key);

        model.addAttribute("productList", productList);

        return "/admin/product/productList";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam List<MultipartFile> attachImage,
                             productDTO product, Model model) {
        // 상품 정보 저장

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof AdminLoginDetails adminLoginDetails) {
                LoginDTO login = adminLoginDetails.getLoginDTO();
                String userId = login.getId();

                /* 상품 등록하기 */
                productDTO newproduct = new productDTO();
                newproduct.setProductName(product.getProductName());
                newproduct.setProductPrice(product.getProductPrice());
                newproduct.setProductStatus(product.getProductStatus());
                newproduct.setProductSummary(product.getProductSummary());
                newproduct.setProductCategoryCode(product.getProductCategoryCode());

                productService.insertProduction(newproduct);


                /* 경로 설정 */
                String fileUploadDir = IMAGE_DIR + "original";

                File dir1 = new File(fileUploadDir);

                /* 디렉토리가 없을 경우 생성한다. */
                if (!dir1.exists()) {
                    dir1.mkdirs();
                }

                /* 업로드 파일에 대한 정보를 담을 리스트 */
                List<ProductImageDTO> imageList = new ArrayList<>();

                try {
                    for (int i = 0; i < attachImage.size(); i++) {
                        /* 첨부파일이 실제로 존재하는 경우 로직 수행 */
                        if (attachImage.get(i).getSize() > 0) {

                            String originalFileName = attachImage.get(i).getOriginalFilename();
                            log.info("originalFileName : {}", originalFileName);

                            String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                            String saveFileName = UUID.randomUUID() + ext;
                            log.info("savedFileName : {}", saveFileName);

                            /* 서버의 설정 디렉토리에 파일 저장하기 */
                            attachImage.get(i).transferTo(new File(fileUploadDir + "/" + saveFileName));

                            /* 가장 최신 상품 조회 */
                            productDTO lastProduct = productService.codePlz();
                            log.info("lastProduct: {}", lastProduct);

                            /* DB에 저장할 파일의 정보 처리 */
                            ProductImageDTO fileInfo = new ProductImageDTO();
                            fileInfo.setSavedName(saveFileName);
                            fileInfo.setSavePath("/static/productUpload/original");
                            fileInfo.setProductCode(lastProduct.getProductCode());

                            /* 리스트에 파일 정보 저장 */
                            imageList.add(fileInfo);
                            log.info("imageList : {}", imageList);
                        }
                    }
                    /* 이미지 리스트를 한 번에 DB에 저장 */
                    productService.registImageList(imageList);

                    model.addAttribute("message", "파일 업로드에 성공하였습니다.");

                } catch (IOException e) {
                    /* 파일 저장 중간에 실패 시, 이전에 저장된 파일 삭제 */

                    model.addAttribute("message", "파일 업로드에 실패하였습니다.");
                }
                log.info("imageList : {}", imageList);
            }



        }            return "redirect:/admin/product/productList";

    }


    //    파라미터 넘긴 방식이 Get 인데 PostMapping 되어있었음
//    html 링크에서 /admin 명시 안되어있음
//    view페이지 오류 ex) 찾을수없는값 있을경우
    @GetMapping("/productView")
    public String productView(@RequestParam("productCode") int productCode, Model model) {
        model.addAttribute("setCondition", "modifyProduct");
        System.out.println(productCode);
        productDTO product = productService.getProductByCode(productCode);
        model.addAttribute("product", product);
        System.out.println(product);
        return "/admin/product/productView";
    }

    @GetMapping("/nullProductView")
    public String getProductViewPage(Model model) {
        model.addAttribute("setCondition", "addProduct");
        return "/admin/product/productView";
    }

    @PostMapping("/productUpdate")
    public String productUpdate(productDTO product, int productCode,
                                @RequestParam List<MultipartFile> attachImage,
                                Model model) throws ProductUpdateException {
        productService.productUpdate(product);

        /* 경로 설정 */
        String fileUploadDir = IMAGE_DIR + "original";

        File dir1 = new File(fileUploadDir);

        /* 디렉토리가 없을 경우 생성한다. */
        if (!dir1.exists()) {
            dir1.mkdirs();
        }

        /* 업로드 파일에 대한 정보를 담을 리스트 */
        List<ProductImageDTO> imageList = new ArrayList<>();

        try {
            for (int i = 0; i < attachImage.size(); i++) {
                /* 첨부파일이 실제로 존재하는 경우 로직 수행 */
                if (attachImage.get(i).getSize() > 0) {
                    System.out.println(attachImage.get(i));
                    String originalFileName = attachImage.get(i).getOriginalFilename();
                    log.info("originalFileName : {}", originalFileName);

                    String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String saveFileName = UUID.randomUUID() + ext;
                    log.info("savedFileName : {}", saveFileName);

                    /* 서버의 설정 디렉토리에 파일 저장하기 */
                    attachImage.get(i).transferTo(new File(fileUploadDir + "/" + saveFileName));

                    /* DB에 저장할 파일의 정보 처리 */
                    ProductImageDTO fileInfo = new ProductImageDTO();
                    fileInfo.setSavedName(saveFileName);
                    fileInfo.setSavePath("/static/customerUpload/original");

                    log.info("productCode : {}", productCode);
                    /* 이미지 DTO에 요청 코드 설정 */
                    fileInfo.setProductCode(productCode);
                    log.info("fileInfo : {}", fileInfo);

                    /* 리스트에 파일 정보 저장 */
                    imageList.add(fileInfo);
                    log.info("imageList : {}", imageList);
                }
            }
            /* 이미지 리스트를 한 번에 DB에 저장 */
            productService.registImageList(imageList);


        } catch (IOException e) {
            /* 파일 저장 중간에 실패 시, 이전에 저장된 파일 삭제 */
            for (ProductImageDTO image : imageList) {
                new File(fileUploadDir + "/" + image.getSavedName()).delete();
            }
            System.out.println("실패");
        }
        log.info("imageList : {}", imageList);

        return "redirect:/admin/product/productList";
    }
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("productCode") int productCode) {
        productService.deleteProductById(productCode);
        return "redirect:/admin/product/productList";
    }
    @GetMapping("/admin/product/search")
    public String searchProductsView(Model model,
                                     @RequestParam(required = false, defaultValue = "") String productName,
                                     @RequestParam(required = false, defaultValue = "0") String productCategoryCodeStr,
                                     @RequestParam(required = false, defaultValue = "") String productStatus) {
        int productCategoryCode;
        try {
            productCategoryCode = Integer.parseInt(productCategoryCodeStr);
        } catch (NumberFormatException e) {
            // 잘못된 값이 들어온 경우 기본값으로 설정하거나 예외 처리
            productCategoryCode = 0;
        }

        List<productDTO> products = productService.searchProducts(productName, productCategoryCode, productStatus);
        model.addAttribute("products", products);

        return "admin/product/productList";
    }





}



