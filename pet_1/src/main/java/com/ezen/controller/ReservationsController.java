// 채완

package com.ezen.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ezen.Repository.MemberRepository;
import com.ezen.entity.Member;
import com.ezen.entity.Reservations;
import com.ezen.reservations.service.ReservationsService;

@Controller
public class ReservationsController {

	@Autowired
	private ReservationsService reservService;
	@Autowired
	private MemberRepository memberRepo;

	
    // ----- 전체 시터 불러오기 -----
    @GetMapping("/AllSitters")
    public String allSitter(@RequestParam("memberType") String memberType, Model model) {
		
    	List<Member> sitterList = reservService.findByMemberType(memberType);

    	model.addAttribute("sitterList", sitterList);
    	
    	return "board/Sitter";
    }
    
    // ----- 시터 상세정보 불러오기 -----
    @GetMapping("/SitterProfile")
    public String getSitter(Member member, Model model) {
    	Optional<Member> sitter = reservService.getSitter(member);
    	//System.out.println("sitter = " + sitter);
    	
    	model.addAttribute("sitter", sitter.get());
    	
    	return "board/SitterProfile";
    }
    
    // ----- 전체 훈련사 불러오기 -----
    @GetMapping("/AllTrainers")
    public String allTrainer(@RequestParam("memberType") String memberType, Model model) {
		
    	List<Member> trainerList = reservService.findByMemberType(memberType);
    	model.addAttribute("trainerList", trainerList);
    	
    	return "board/Trainer.html";
    }
    
    // ----- 훈련사 상세정보 불러오기 -----
    @GetMapping("/TrainerProfile")
    public String getTrainer(Member member, Model model) {
    	Optional<Member> trainer = reservService.getTrainer(member);
    	//System.out.println("sitter = " + sitter);
    	
    	model.addAttribute("trainer", trainer.get());
    	
    	return "board/TrainerProfile.html";
    }
    
	// ----- 펫시터 예약화면 호출 -----
    @GetMapping("/SitterBooking")
    public String sitterbookingForm() {
    	
        return "board/SitterBooking.html";
    }
    
    // ----- 펫시터 예약 처리 -----
	@PostMapping("/SitterBooking")
	public String sitterReservations(Reservations reservations, Principal principal) {
		Member member = memberRepo.findByMemberId(principal.getName()).get();
	    reservations.setMember(member);
	    reservService.insertReservations(reservations);
	      
	    return "redirect:/ReservationList";
	}

	// ----- 훈련사 예약화면 호출 -----
    @GetMapping("/TrainerBooking")
    public String trainerbookingForm() {
        return "board/TrainerBooking.html";
    }
	
    // ----- 훈련사 예약 처리 -----
	@PostMapping("/TrainerBooking")
	public String trainerReservations(Reservations reservations, Principal principal) {
		Member member = memberRepo.findByMemberId(principal.getName()).get();
	    reservations.setMember(member);
	    reservService.insertReservations(reservations);
	      
	    return "redirect:/ReservationList";
	}

	// ----- 예약 취소 -----
	@GetMapping("/DeleteReservation")
	public String deleteReservations(@RequestParam(value="rsSeq", required=false) Integer[] valueArr) {

		for(int i = 0; i < valueArr.length; i++) {
			reservService.deleteReservations(valueArr[i]);	
		}
		
		return "redirect:/ReservationList";
	}
	
	// ----- 예약 목록 조회 -----
	@GetMapping("/ReservationList")
	public String reservationList(Reservations reservations, Model model,Principal principal,
	         @PageableDefault(page = 0, size = 10, sort = "rsSeq", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<Reservations> reservationList = reservService.findReservationsByMemberId(principal.getName(), pageable);
		
		model.addAttribute("reservationList", reservationList);
		
		return "member/MyPage_Reservation.html";
	}

}


