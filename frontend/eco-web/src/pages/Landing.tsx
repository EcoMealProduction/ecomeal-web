
import React from 'react';
import { HeroSection } from '@/components/landing/HeroSection';
import { FeaturesSection } from '@/components/landing/FeaturesSection';
import { PartnersSection } from '@/components/landing/PartnersSection';
import { HowItWorksSection } from '@/components/landing/HowItWorksSection';
import { TestimonialsSection } from '@/components/landing/TestimonialsSection';
import { Footer } from '@/components/landing/Footer';
import { Navbar } from '@/components/landing/Navbar';
import { ImpactSection } from '@/components/landing/ImpactSection';

const Landing = () => {
  return (
    <div className="min-h-screen bg-eco-bg">
      <Navbar />
      <HeroSection />
      <FeaturesSection />
      <HowItWorksSection />
      <PartnersSection />
      <ImpactSection />
      <TestimonialsSection />
      <Footer />
    </div>
  );
};

export default Landing;
