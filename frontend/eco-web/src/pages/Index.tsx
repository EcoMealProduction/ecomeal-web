
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Index = () => {
  const navigate = useNavigate();

  useEffect(() => {
    // For demo purposes, always show onboarding
    // In a real app, you'd check if user has completed onboarding
    const hasCompletedOnboarding = localStorage.getItem('ecomeal_onboarding_complete');
    
    if (!hasCompletedOnboarding) {
      navigate('/onboarding');
    }
  }, [navigate]);

  // This will redirect to onboarding, so we don't need to render anything
  return null;
};

export default Index;
